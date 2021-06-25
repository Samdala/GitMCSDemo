package test.energy.abatementmeasures;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;



import test.energy.EnergyBaseTestSuite;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class AnalysisAbatementMeasuresPageObject extends EnergyBaseTestSuite {


	protected final String ADD_ABATEMENT_MEASURE_FORM_CLASS = "slnmAMId";

	protected final String ADD_ABATEMENT_MEASURE_FOOTER_CLASS = "x-window-footer x-panel-btns";
	
	protected final String ABATEMENT_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	
	
	public void goToDefinition(String definition) {
		driver.get(configuration.getApplicationUrl() + "/frame.php?relay="
				+ definition);
	};

	public void expandAnalysis() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"span", "text()", "Analysis", true, true).click();
		Reporter.log("Expand analysis", true);
	}

	public void openAnalysisEntity(String entity) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"button", "text()", entity, true, true).click();
		Reporter.log("open analysis " + entity, true);
	}

	public void clickAddButton(String gridClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("click add button", true);
	}

	public void clickEditButton(String gridClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("click edit button", true);
	}

	public void clickDeleteButton(String gridClass) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		clickOnDialogButton("Yes");
		clickOnDialogButton("OK");
		
		Reporter.log("Delete selected abatement measure" + " (" + timer.stop() + "ms)", true);
	}
	

	public void setDate(String code) {
		WebElement date = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input", "@name",
				"measureDate", true, true);
		date.clear();
		date.sendKeys(code);
		Reporter.log("set date " + code, true);
	}

	public String getDate() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input",
				"@name", "measureDate", true, true).getAttribute("value");
	}


	public void setTotalCost(String code) {
		WebElement date = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input", "@name",
				"totalCost", true, true);
		date.clear();
		date.sendKeys(code);
		Reporter.log("set total cost " + code, true);
	}

	public String getTotalCost() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input",
				"@name", "totalCost", true, true).getAttribute("value");
	}


	public void setSubsidy(String code) {
		WebElement date = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input", "@name",
				"subsidy", true, true);
		date.clear();
		date.sendKeys(code);
		Reporter.log("set subsidy " + code, true);
	}

	public String getSubsidy() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input",
				"@name", "subsidy", true, true).getAttribute("value");
	}

	public void setCostSaving(String code) {
		WebElement date = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input", "@name",
				"costSaving", true, true);
		date.clear();
		date.sendKeys(code);
		Reporter.log("set cost saving " + code, true);
	}

	public String getCostSaving() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input",
				"@name", "costSaving", true, true).getAttribute("value");
	}
	

	public void setAnnualCommoditySaving(String code) {
		WebElement date = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input", "@name",
				"commoditySaving", true, true);
		date.clear();
		date.sendKeys(code);
		Reporter.log("set date " + code, true);
	}

	public String getAnnualCommoditySaving() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input",
				"@name", "commoditySaving", true, true).getAttribute("value");
	}

	
	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input",
				"@name", "reference", true, true).getAttribute("value");
	}

	public void setReference(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input", "@name",
				"reference", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("set reference " + reference, true);
	}

	public String getPaybackPeriod() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input",
				"@name", "paybackPeriod", true, true).getAttribute("value");
	}
	

	public String getAnnualEmissionAbatement() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input",
				"@name", "emissionAbatement", true, true).getAttribute("value");
	}

	
	public String getMarginalAbatementCost() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input",
				"@name", "amCostIndex", true, true).getAttribute("value");
	}

	public String getReturnCapitalInvestment() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input",
				"@name", "returnInvestment", true, true).getAttribute("value");
	}


	public void setCostTypeExtjscript(String className) {
		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input",
				"@name", "costType", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, className);
		Reporter.log("set cost type " + className, true);
	}


	public String getCostType() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input",
				"@name", "costType", true, true).getAttribute("value");
	}

	public void setCurrencyExtjscript(String className) {
		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input",
				"@name", "currencyId", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, className);
		Reporter.log("set cost type " + className, true);
	}


	public String getCurrency() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_ABATEMENT_MEASURE_FORM_CLASS, "input",
				"@name", "currencyId", true, true).getAttribute("value");
	}

	
	public void setProposer(String proposerName){
		clickLookup(ADD_ABATEMENT_MEASURE_FORM_CLASS, "proposer");
		setValueGridLookupWithFilters(proposerName, "Reference");
//		setValueGridLookup(proposerName);
//		waitForExtJSAjaxComplete(5);
	}
	
	
	public String getProposer() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='proposer']//..//input)[last()]"))
				.getAttribute("value");
	}
	

	public void setStatus(String proposerName){
		clickLookup(ADD_ABATEMENT_MEASURE_FORM_CLASS, "amStatus");
		setValueGridLookupWithFilters(proposerName, "Reference");
//		setValueGridLookup(proposerName);
//		waitForExtJSAjaxComplete(5);
	}
	
	
	public String getStatus() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='amStatus']//..//input)[last()]"))
				.getAttribute("value");
	}


	public void setType(String proposerName){
		clickLookup(ADD_ABATEMENT_MEASURE_FORM_CLASS, "amType");
		setValueGridLookupWithFilters(proposerName, "Reference");
//		setValueGridLookup(proposerName);
//		waitForExtJSAjaxComplete(5);
	}
	
	
	public String getType() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='amType']//..//input)[last()]"))
				.getAttribute("value");
	}


	public void setCommodity(String proposerName){
		clickLookup(ADD_ABATEMENT_MEASURE_FORM_CLASS, "commodity");
		//setValueGridLookup(proposerName);
		setValueGridLookupWithFilters(proposerName, "Reference");
		//waitForExtJSAjaxComplete(15);
	}
	
	
	public String getCommodity() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='commodity']//..//input)[last()]"))
				.getAttribute("value");
	}


	public void setCommodityEndUse(String proposerName) {
		clickLookup(ADD_ABATEMENT_MEASURE_FORM_CLASS, "commodityEndUse");
//		setValueGridLookup(proposerName);
		setValueGridLookupWithFilters(proposerName, "Reference");
//		waitForExtJSAjaxComplete(5);
	}

	public String getCommodityEndUse() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='commodityEndUse']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setAnnualCommoditySavingUom(String proposerName) {

		setInputLookup(proposerName, "commoditySavingUom", ADD_ABATEMENT_MEASURE_FORM_CLASS);
//		clickLookup(ADD_ABATEMENT_MEASURE_FORM_CLASS, "commoditySavingUom");
//		
//		setValueGridLookupExtJs(proposerName);
//		
//		setValueGridLookup(proposerName);
//		waitForExtJSAjaxComplete(5);
	}

	public String getAnnualCommoditySavingUom() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='commoditySavingUom']//..//input)[last()]"))
				.getAttribute("value");
	}

	
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_ABATEMENT_MEASURE_FOOTER_CLASS, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForMaskDisappear();
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", ADD_ABATEMENT_MEASURE_FOOTER_CLASS, "button", "text()",
				"Close", true, true).click();
		waitForMaskDisappear();
	}
}
