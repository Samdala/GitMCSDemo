package test.energy.benchmarks;

//import java.util.concurrent.TimeUnit;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.seleniumemulation.IsElementPresent;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.energy.EnergyBaseTestSuite;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class ConfigurationBenchmarksPageObject extends EnergyBaseTestSuite {

	protected final String ADD_BENCHMARKS_FORM_CLASS = "slnmBenchmarkId";
	
	protected final String ADD_BENCHMARKS_FULL_XPATH = "//div[contains(@class, 'slnmBenchmarkId')]";
	
	protected final String ADD_BENCHMARKS_PROPERTIES_TAB = "//div[contains(@class, 'slnmBenchmarkId')]//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top')]/div[1]";
	
	protected final String ADD_BENCHMARKS_NRG_OBJ_TYPE_TAB = "//div[contains(@class, 'slnmBenchmarkId')]//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top')]/div[2]";
	
	protected final String ADD_BENCHMARKS_ENERGY_TAB = "//div[contains(@class, 'slnmBenchmarkId')]//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top')]/div[3]";
	
	protected final String ADD_BENCHMARKS_DOCUMENTS_TAB = "//div[contains(@class, 'slnmBenchmarkId')]//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top')]/div[4]";

	protected final String ADD_BENCHMARKS_FOOTER_CLASS = "x-window-footer x-panel-btns";
	
	protected final String BENCHMARKS_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	
	protected final String XPATH_NEW_TRANSACTION_LINES_GRID_EDITOR = "//div[contains(@class, 'x-grid-panel') and not (contains(@style, 'visibility: hidden'))]";
	
	protected final String ELECTRICITY_TAB_XPATH = "//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//li[contains(@id, 'divisionElectricity')]"
									+ "//..//..//..//..//div[@class='x-tab-panel-bwrap']/div/div[1]";
	protected final String COMBUSTIBLES_TAB_XPATH = "//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//li[contains(@id, 'divisionCombustibles')]"
									+ "//..//..//..//..//div[@class='x-tab-panel-bwrap']/div/div[2]";
	protected final String WATER_TAB_XPATH = "//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//li[contains(@id, 'divisionWater')]"
									+ "//..//..//..//..//div[@class='x-tab-panel-bwrap']/div/div[3]";
	
	static Integer codeIncrement = 0;
	static Integer referenceIncrement = 0;
	static Integer yearIncrement = 0;


	public void goToDefinition(String definition) {
		driver.get(configuration.getApplicationUrl() + "/frame.php?relay="
				+ definition);
	};

	public void expandConfiguration() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"span", "text()", "Configuration", true, true).click();
		Reporter.log("Expand Configuration", true);
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
		Reporter.log("Click Add button", true);
	}

	public void clickEditButton(String gridClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("Click Edit button", true);
	}

	public void clickDeleteButton(String gridClass) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		clickOnDialogButton("Yes");
		clickOnDialogButton("OK");
		
		Reporter.log("Delete Entity" + " (" + timer.stop() + "ms)", true);
	}
	
	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_BENCHMARKS_FORM_CLASS, "input",
				"@name", "reference", true, true).getAttribute("value");
	}

	public void setReference(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_BENCHMARKS_FORM_CLASS, "input", "@name",
				"reference", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("Set Reference " + reference, true);
	}
	
	public String getBenchmarkClass() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_BENCHMARKS_FORM_CLASS, "input",
				"@name", "benchmarkClassName", false, false).getAttribute("value");
	}
	
	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_BENCHMARKS_FORM_CLASS, "input",
				"@name", "code", true, true).getAttribute("value");
	}
	
	public void setCode(String code) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_BENCHMARKS_FORM_CLASS, "input", "@name",
				"code", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(code);
		Reporter.log("set code " + code, true);
	}
	
	public String getDescription() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_BENCHMARKS_FORM_CLASS, "textarea",
				"@name", "description", true, true).getAttribute("value");
	}

	public void setDescription(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_BENCHMARKS_FORM_CLASS, "textarea", "@name",
				"description", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("set reference " + reference, true);
	}
	
	
	public String getYear() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_BENCHMARKS_FORM_CLASS, "input",
				"@name", "year", true, true).getAttribute("value");
	}

	public void setYear(String year) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_BENCHMARKS_FORM_CLASS, "input", "@name",
				"year", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(year);
		Reporter.log("set year " + year, true);
	}
	
	public void setEnergyLevel(String energyLevel) {
		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_BENCHMARKS_FORM_CLASS, "input",
				"@name", "energyLevel", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, energyLevel);
		Reporter.log("energy Level " + energyLevel, true);
	}
	
	public String getEnergyLevel() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_BENCHMARKS_FORM_CLASS, "input",
				"@name", "energyLevel", false, false).getAttribute("value");
	}
	
	public boolean verifyEnergyLevelDisabled() {
		if (driver.findElement(By.xpath("//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//input[@name='energyLevel']//..//../div")).getAttribute("class").contains("x-item-disabled"))
			return true;
		else
			return false;
	}
	
	public void setOrigin(String origin) {
		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_BENCHMARKS_FORM_CLASS, "input",
				"@name", "origin", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, origin);
		Reporter.log("origin " + origin, true);
	}
	
	public String getOrigin() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_BENCHMARKS_FORM_CLASS, "input",
				"@name", "origin", false, false).getAttribute("value");
	}
	
	public void setEvaluationBasis(String evaluationBasis) {
		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_BENCHMARKS_FORM_CLASS, "input",
				"@name", "evaluationBasis", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, evaluationBasis);
		Reporter.log("origin " + evaluationBasis, true);
	}
	
	public String getEvaluationBasis() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_BENCHMARKS_FORM_CLASS, "input",
				"@name", "evaluationBasis", false, false).getAttribute("value");
	}
	
	public void setWeatherStation(String weatherStation){
		clickLookUpWeatherStation(ADD_BENCHMARKS_FORM_CLASS);
		String lookupId = getXWindowId("Select a Weather Station");
		setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, weatherStation, "Code");
	}
	
	public String getWeatherStation() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'slnmBenchmarkId')]//div[contains(@class, 'x-form-item')]//label[contains(text(), 'Weather Station')]//..//input)"))
				.getAttribute("value");
	}
	
	public boolean verifyWeatherStationFieldDisabled() {
		if (driver
				.findElement(
						By.xpath("//div[contains(@class,'slnmBenchmarkId')]//div[contains(@class, 'x-form-item')]//label[contains(text(), 'Weather Station')]//..//input")).getAttribute("disabled") != null)
			return true;
		else
			return false;
			
	}
	
	public void setMetricOnElectricityTab(String electricityMetricName){
		driver
		.findElement(
				By.xpath("(//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//input[@name='metric']//..//input[last()])[1]//..//..//button")).click();
		waitForExtJSAjaxComplete(5);
		String lookupId = getXWindowId("Select a Metric");
		setValueGridLookup("@id", lookupId, electricityMetricName);
	}
	
	public String getMetricOnElectricityTab() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//input[@name='metric']//..//input[last()])[1]"))
				.getAttribute("value");
	}
	
	public void clearMetricOnElectricityTab () {
		WebElement webElement = driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//input[@name='metric']//..//input[last()])[1]"));
		webElement.sendKeys("1");
		webElement.clear();
	}
	
	public void setMetricOnCombustiblesTab(String electricityMetricName){
		driver
		.findElement(
				By.xpath("(//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//input[@name='metric']//..//input[last()])[2]//..//..//button")).click();
		waitForExtJSAjaxComplete(5);
		String lookupId = getXWindowId("Select a Metric");
		setValueGridLookup("@id", lookupId, electricityMetricName);
	}
	
	public String getMetricOnCombustiblesTab() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//input[@name='metric']//..//input[last()])[2]"))
				.getAttribute("value");
	}
	
	public void clearMetricOnCombustiblesTab () {
		WebElement webElement = driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//input[@name='metric']//..//input[last()])[2]"));
		webElement.sendKeys("1");
		webElement.clear();
	}
	
	public void setMetricOnWaterTab(String electricityMetricName){
		driver
		.findElement(
				By.xpath("(//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//input[@name='metric']//..//input[last()])[3]//..//..//button")).click();
		waitForExtJSAjaxComplete(5);
		String lookupId = getXWindowId("Select a Metric");
		setValueGridLookup("@id", lookupId, electricityMetricName);
	}
	
	public String getMetricOnWaterTab() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//input[@name='metric']//..//input[last()])[3]"))
				.getAttribute("value");
	}
	
	public void clearMetricOnWaterTab () {
		WebElement webElement = driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//input[@name='metric']//..//input[last()])[3]"));
		webElement.sendKeys("1");
		webElement.clear();
	}

	public void setPrimaryEnergyFactorDependingOnTab(String primaryEnergyFactor, String tabName) {
		WebElement field = null;
		switch (tabName) {
			case "Electricity": field = driver
					.findElement(
							By.xpath(ELECTRICITY_TAB_XPATH+"//input[@name='primaryEnergyFactor']"));
			break;
			case "Combustibles": field = driver
					.findElement(
							By.xpath(COMBUSTIBLES_TAB_XPATH+"//input[@name='primaryEnergyFactor']"));
			break;
			case "Water": field = driver
					.findElement(
							By.xpath(WATER_TAB_XPATH+"//input[@name='primaryEnergyFactor']"));
			break;
		}
		field.click();
		field.clear();
		field.sendKeys(primaryEnergyFactor);
		Reporter.log("Set Primary Energy Factor - " + primaryEnergyFactor+" on "+tabName+" tab", true);
	}
	
	public String getPrimaryEnergyFactorDependingOnTab(String tabName) {
		switch (tabName) {
		case "Electricity": return driver
				.findElement(
						By.xpath(ELECTRICITY_TAB_XPATH+"//input[@name='primaryEnergyFactor']"))
				.getAttribute("value");
		case "Combustibles": return driver
				.findElement(
						By.xpath(COMBUSTIBLES_TAB_XPATH+"//input[@name='primaryEnergyFactor']"))
				.getAttribute("value");
		case "Water": return driver
				.findElement(
						By.xpath(WATER_TAB_XPATH+"//input[@name='primaryEnergyFactor']"))
				.getAttribute("value");
		default: return "You forgot to mention the name of the tab";
		}
	}
	
	public boolean verifyPrimaryEnergyFactorIsNotDisplayed(String tabName) {
		switch (tabName) {
		case "Electricity": if (driver
				.findElement(
						By.xpath(ELECTRICITY_TAB_XPATH+"//input[@name='primaryEnergyFactor']"))
				.getAttribute("class").contains("x-hide-display"))
			return true;
		else
			return false;
		
		case "Combustibles": if (driver
				.findElement(
						By.xpath(COMBUSTIBLES_TAB_XPATH+"//input[@name='primaryEnergyFactor']"))
				.getAttribute("class").contains("x-hide-display"))
			return true;
		else
			return false;
		
		case "Water": if (driver
				.findElement(
						By.xpath(WATER_TAB_XPATH+"//input[@name='primaryEnergyFactor']"))
				.getAttribute("class").contains("x-hide-display"))
			return true;
		else
			return false;

		default: return false;
		}
	}
	
	public void clearPrimaryEnergyFactorDependingOnTab(String tabName) {
		WebElement field = null;
		switch (tabName) {
			case "Electricity": field = driver
					.findElement(
							By.xpath(ELECTRICITY_TAB_XPATH+"//input[@name='primaryEnergyFactor']"));
			break;
			case "Combustibles": field = driver
					.findElement(
							By.xpath(COMBUSTIBLES_TAB_XPATH+"//input[@name='primaryEnergyFactor']"));
			break;
			case "Water": field = driver
					.findElement(
							By.xpath(WATER_TAB_XPATH+"//input[@name='primaryEnergyFactor']"));
			break;
		}
		field.click();
		field.sendKeys("1");
		field.clear();
		Reporter.log("Clear Primary Energy Factor - on "+tabName+" tab", true);
	}
	
	public void setLowUsageDependingOnTab(String fieldValue, String tabName) throws InterruptedException {
		WebElement field = null;
		switch (tabName) {
			case "Electricity": 
				superClick(ELECTRICITY_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-3')]", ELECTRICITY_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][2]//input");
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(ELECTRICITY_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][2]//input"));
			break;
			case "Combustibles": 
				superClick(COMBUSTIBLES_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-3')]", COMBUSTIBLES_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][2]//input");
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(COMBUSTIBLES_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][2]//input"));
			break;
			case "Water": 
				superClick(WATER_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-2')]", WATER_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][1]//input");
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(WATER_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][1]//input"));
			break;
		}
//		field.click();
		field.clear();
		field.sendKeys(fieldValue);
		Reporter.log("Set Low Usage - " +fieldValue+" on "+tabName+" tab", true);
	}
	
	//TODO Changed from getText to getAttribute(value)
	public String getLowUsageDependingOnTab(String tabName, String energyObjType) {
		switch (tabName) {
			case "Electricity": return driver
					.findElement(
							By.xpath(ELECTRICITY_TAB_XPATH+"//div[text()='"+energyObjType+"']//..//..//div[contains(@class, 'x-grid3-col-3')]")).getText();
			case "Combustibles": return driver
					.findElement(
							By.xpath(COMBUSTIBLES_TAB_XPATH+"//div[text()='"+energyObjType+"']//..//..//div[contains(@class, 'x-grid3-col-3')]")).getText();
			case "Water": return driver
					.findElement(
							By.xpath(WATER_TAB_XPATH+"//div[text()='"+energyObjType+"']//..//..//div[contains(@class, 'x-grid3-col-2')]")).getText();
			default: return "You forgot to mention the name of the tab";
		}
	}
	
//	public void superClick(String elementToClick, String elementToWait) throws InterruptedException {
//		Integer loopIterator=0;
//		
//		WebDriverWait wait = new WebDriverWait(driver, 3);
//		
//		driver.findElement(By.xpath(elementToClick)).click();
//		Thread.sleep(500);
//		
//		while (!McsElement.isElementDisplayed(driver, elementToWait) && loopIterator<=5) {
//			driver.findElement(By.xpath(elementToClick)).click();
//			try {
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementToWait)));
//			} catch (Exception e) {
//				loopIterator += 1;
//			}
//		}
//	}
	
	public void setAverageDependingOnTab(String fieldValue, String tabName) throws InterruptedException {
		WebElement field = null;
		
		switch (tabName) {
			case "Electricity": 
				superClick(ELECTRICITY_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-3')]", ELECTRICITY_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][2]//input");
				field = driver
						.findElement(
								By.xpath(ELECTRICITY_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][2]//input"));
			break;
			case "Combustibles": 
				superClick(COMBUSTIBLES_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-3')]", COMBUSTIBLES_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][2]//input");
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(COMBUSTIBLES_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][2]//input"));
			break;
			case "Water": 
				superClick(WATER_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-2')]", WATER_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][1]//input");
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(WATER_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][1]//input"));
			break;
		}
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", field);
		field.clear();
		field.sendKeys(fieldValue);
		field.sendKeys(Keys.ENTER);
		Reporter.log("Set Low Usage - " +fieldValue+" on "+tabName+" tab", true);
	}

	public String getAverageDependingOnTab(String tabName) {
		switch (tabName) {
			case "Electricity": return driver
					.findElement(
							By.xpath(ELECTRICITY_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-3')]")).getText();
			case "Combustibles": return driver
					.findElement(
							By.xpath(COMBUSTIBLES_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-3')]")).getText();
			case "Water": return driver
					.findElement(
							By.xpath(WATER_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-2')]")).getText();
			default: return "You forgot to mention the name of the tab";
		}
	}
	
	public void setMediumUsageDependingOnTab(String fieldValue, String tabName) throws InterruptedException {
		WebElement field = null;
		switch (tabName) {
			case "Electricity": 
				superClick(ELECTRICITY_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-4')]", ELECTRICITY_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][3]//input");
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(ELECTRICITY_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][3]//input"));
			break;
			case "Combustibles":
				superClick(COMBUSTIBLES_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-4')]", COMBUSTIBLES_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][3]//input");
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(COMBUSTIBLES_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][3]//input"));
			break;
			case "Water": 
				superClick(WATER_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-3')]", WATER_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][2]//input");
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(WATER_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][2]//input"));
			break;
		}
//		field.click();
		field.clear();
		field.sendKeys(fieldValue);
		Reporter.log("Set Medium Usage - " +fieldValue+" on "+tabName+" tab", true);
	}
	
	public String getMediumUsageDependingOnTab(String tabName) {
		switch (tabName) {
			case "Electricity": return driver
					.findElement(
							By.xpath(ELECTRICITY_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-4')]")).getText();
			case "Combustibles": return driver
					.findElement(
							By.xpath(COMBUSTIBLES_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-4')]")).getText();
			case "Water": return driver
					.findElement(
							By.xpath(WATER_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-3')]")).getText();
			default: return "You forgot to mention the name of the tab";
		}
	}
	
	public void setHighUsageDependingOnTab(String fieldValue, String tabName) throws InterruptedException {
		WebElement field = null;
		switch (tabName) {
			case "Electricity": 
				superClick(ELECTRICITY_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-5')]", ELECTRICITY_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][4]//input");
				driver
					.findElement(
							By.xpath(ELECTRICITY_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-5')]")).click();
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(ELECTRICITY_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][4]//input"));
			break;
			case "Combustibles": 
				superClick(COMBUSTIBLES_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-5')]", COMBUSTIBLES_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][4]//input");
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(COMBUSTIBLES_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][4]//input"));
			break;
			case "Water": 
				superClick(WATER_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-4')]", WATER_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][3]//input");
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(WATER_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][3]//input"));
			break;
		}
//		field.click();
		field.clear();
		field.sendKeys(fieldValue);
		Reporter.log("Set High Usage - " +fieldValue+" on "+tabName+" tab", true);
	}
	
	public String getHighUsageDependingOnTab(String tabName) {
		switch (tabName) {
			case "Electricity": return driver
					.findElement(
							By.xpath(ELECTRICITY_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-5')]")).getText();
			case "Combustibles": return driver
					.findElement(
							By.xpath(COMBUSTIBLES_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-5')]")).getText();
			case "Water": return driver
					.findElement(
							By.xpath(WATER_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-4')]")).getText();
			default: return "You forgot to mention the name of the tab";
		}
	}
	
	public void setNumberOfEnergyObjectsDependingOnTab(String fieldValue, String tabName) throws InterruptedException {
		WebElement field = null;
		switch (tabName) {
			case "Electricity": 
				superClick(ELECTRICITY_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-6')]", ELECTRICITY_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][5]//input");
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(ELECTRICITY_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][5]//input"));
			break;
			case "Combustibles": 
				superClick(COMBUSTIBLES_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-6')]", COMBUSTIBLES_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][5]//input");
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(COMBUSTIBLES_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][5]//input"));
			break;
			case "Water": 
				superClick(WATER_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-5')]", WATER_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][4]//input");
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(WATER_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][4]//input"));
			break;
		}
//		field.click();
		field.clear();
		field.sendKeys(fieldValue);
		Reporter.log("Set Number Of Energy Objects - " +fieldValue+" on "+tabName+" tab", true);
	}
	
	public String getNumberOfEnergyObjectsDependingOnTab(String tabName) {
		switch (tabName) {
			case "Electricity": return driver
					.findElement(
							By.xpath(ELECTRICITY_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-6')]")).getText();
			case "Combustibles": return driver
					.findElement(
							By.xpath(COMBUSTIBLES_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-6')]")).getText();
			case "Water": return driver
					.findElement(
							By.xpath(WATER_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-5')]")).getText();
			default: return "You forgot to mention the name of the tab";
		}
	}
	
	public void setNumberOfEnergyObjectsForExternalBenhcmarkDependingOnTab(String fieldValue, String tabName) throws InterruptedException {
		WebElement field = null;
		switch (tabName) {
			case "Electricity": 
				superClick(ELECTRICITY_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-4')]", ELECTRICITY_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][3]//input");
				waitForExtJSAjaxComplete(5);
				field = driver
						.findElement(
								By.xpath(ELECTRICITY_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][3]//input"));
			break;
			case "Combustibles": 
				superClick(COMBUSTIBLES_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-4')]", COMBUSTIBLES_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][3]//input");
			waitForExtJSAjaxComplete(5);
			field = driver
					.findElement(
							By.xpath(COMBUSTIBLES_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][3]//input"));
			break;
			case "Water": 
				superClick(WATER_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-3')]", WATER_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][2]//input");
			waitForExtJSAjaxComplete(5);
			field = driver
					.findElement(
							By.xpath(WATER_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][2]//input"));
			break;
		}
//		field.click();
		field.clear();
		field.sendKeys(fieldValue);
		Reporter.log("Set Number Of Energy Objects - " +fieldValue+" on "+tabName+" tab", true);
	}
	
	public String getNumberOfEnergyObjectsForExternalBenhcmarkDependingOnTab(String tabName) {
		switch (tabName) {
			case "Electricity": return driver
					.findElement(
							By.xpath(ELECTRICITY_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-4')]")).getText();
			case "Combustibles": return driver
					.findElement(
							By.xpath(COMBUSTIBLES_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-4')]")).getText();
			case "Water": return driver
					.findElement(
							By.xpath(WATER_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-3')]")).getText();
			default: return "You forgot to mention the name of the tab";
		}
	}
	
	public void setCDD(String fieldValue) throws InterruptedException {
		String cellXpath = ELECTRICITY_TAB_XPATH+"//div[@class='x-grid3-scroller']//div[contains(@class, 'x-grid3-col-2')]";
		String fieldXpath = ELECTRICITY_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][last()]//input";
		
		superClick(cellXpath, fieldXpath);
		waitForExtJSAjaxComplete(5);
		WebElement field = driver.findElement(By.xpath(fieldXpath));
		field.click();
		field.clear();
		field.sendKeys(fieldValue);
		Reporter.log("Set CDD - " +fieldValue, true);
	}
	
	public String getCDD() {
		return driver
				.findElement(
						By.xpath(ELECTRICITY_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-2')]")).getText();
	}
	
	public void setHDD(String fieldValue) throws InterruptedException {
		String cellXpath = COMBUSTIBLES_TAB_XPATH+"//div[@class='x-grid3-scroller']//div[contains(@class, 'x-grid3-col-2')]";
		String fieldXpath = COMBUSTIBLES_TAB_XPATH+"//div[@class='x-grid3-scroller']/div[contains(@class, 'x-editor')][last()]//input";
		
		superClick(cellXpath, fieldXpath);
		waitForExtJSAjaxComplete(5);
		WebElement field = driver.findElement(By.xpath(fieldXpath));
		field.click();
		field.clear();
		field.sendKeys(fieldValue);
		Reporter.log("Set HDD - " +fieldValue, true);
	}
	
	public String getHDD() {
		return driver
				.findElement(
				By.xpath(COMBUSTIBLES_TAB_XPATH+"//div[contains(@class, 'x-grid3-col-2')]")).getText();
	}
	
	public void setStatusDependingOnTab(String fieldValue, String tabName) {
		String elementId = null;
		switch (tabName) {
			case "Electricity": elementId = driver
					.findElement(
							By.xpath(ELECTRICITY_TAB_XPATH+"//input[@name='status']")).getAttribute("Id");
			break;
			case "Combustibles": elementId = driver
					.findElement(
							By.xpath(COMBUSTIBLES_TAB_XPATH+"//input[@name='status']")).getAttribute("Id");
			break;
			case "Water": elementId = driver
					.findElement(
							By.xpath(WATER_TAB_XPATH+"//input[@name='status']")).getAttribute("Id");
			break;
		}
		DropDown.setExtJsComboValue(driver, elementId, fieldValue);
		Reporter.log("Set Status - "+fieldValue+" on "+tabName+" tab", true);
	}
	
	public String getStatusDependingOnTab(String tabName) {
		switch (tabName) {
			case "Electricity": return driver
					.findElement(
							By.xpath(ELECTRICITY_TAB_XPATH+"//input[@name='status']")).getAttribute("value");
			case "Combustibles": return driver
					.findElement(
							By.xpath(COMBUSTIBLES_TAB_XPATH+"//input[@name='status']")).getAttribute("value");
			case "Water": return driver
					.findElement(
							By.xpath(WATER_TAB_XPATH+"//input[@name='status']")).getAttribute("value");
			default: return "You forgot to mention the name of the tab";
		}
	}
	
	public void setPercentiles(String rowNumber, String percentileValue, String percentileName, String percentileNumber) {
		driver
		.findElement(
				By.xpath(ADD_BENCHMARKS_FULL_XPATH+"//div[@class='x-grid3-body']/div["+rowNumber+"]//")).click();
	}
	
	public void setPercentilesDependingOnRowAndColumn(String rowNumber, String columnName, String fieldValue) throws InterruptedException {
		String cellXpath = null;
		String fieldXpath = ADD_BENCHMARKS_FULL_XPATH+"//div[@class='x-grid3-scroller']//input[contains(@class, 'x-form-focus')]";
		
		switch (columnName) {
		case "Percentile Value":
			cellXpath = ADD_BENCHMARKS_FULL_XPATH+"//div[@class='x-grid3-body']/div["+rowNumber+"]//tr/td[1]/div";
			break;
		case "Percentile Label":
			cellXpath = ADD_BENCHMARKS_FULL_XPATH+"//div[@class='x-grid3-body']/div["+rowNumber+"]//tr/td[2]/div";
			break;	
		case "Percentile Color":
			cellXpath = ADD_BENCHMARKS_FULL_XPATH+"//div[@class='x-grid3-body']/div["+rowNumber+"]//tr/td[3]/div";
			break;
		}
		superClick(cellXpath, fieldXpath);
		waitForExtJSAjaxComplete(5);
		WebElement field = driver.findElement(By.xpath(fieldXpath));	
		field.click();
		field.clear();
		field.sendKeys(fieldValue);
		Reporter.log("Set "+columnName+" - " +fieldValue+ " on " +rowNumber+ " row", true);
	}
	
	public String getPercentilesDependingOnRowAndColumn(String rowNumber, String columnName) {
		switch (columnName) {
		case "Percentile Value":
			return driver
			.findElement(
					By.xpath(ADD_BENCHMARKS_PROPERTIES_TAB+"//div[@class='x-grid3-body']/div["+rowNumber+"]//tr/td[1]/div")).getAttribute("value");
		case "Percentile Label":
			return driver
			.findElement(
					By.xpath(ADD_BENCHMARKS_PROPERTIES_TAB+"//div[@class='x-grid3-body']/div["+rowNumber+"]//tr/td[2]/div")).getAttribute("value");
		case "Percentile Color":
			return driver
			.findElement(
					By.xpath(ADD_BENCHMARKS_PROPERTIES_TAB+"//div[@class='x-grid3-body']/div["+rowNumber+"]//tr/td[3]/div")).getAttribute("value");
		default: return ("Column Name is wrong");	
		}
	}
	
	public String getPercentilesDependingOnRowAndColumnOnInitialForm(String rowNumber, String columnName) {
		switch (columnName) {
		case "Percentile Value":
			return driver
			.findElement(
					By.xpath(ADD_BENCHMARKS_FULL_XPATH+"//div[@class='x-grid3-body']/div["+rowNumber+"]//tr/td[1]/div")).getAttribute("innerHTML");
		case "Percentile Label":
			return driver
			.findElement(
					By.xpath(ADD_BENCHMARKS_FULL_XPATH+"//div[@class='x-grid3-body']/div["+rowNumber+"]//tr/td[2]/div")).getAttribute("innerHTML");
		case "Percentile Color":
			return driver
			.findElement(
					By.xpath(ADD_BENCHMARKS_FULL_XPATH+"//div[@class='x-grid3-body']/div["+rowNumber+"]//tr/td[3]/div")).getAttribute("innerHTML");
		default: return ("Column Name is wrong");	
		}
	}
	
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_BENCHMARKS_FORM_CLASS, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		//waitForMaskDisappear();
		waitForExtJSAjaxComplete(5);
		Reporter.log("Click Save and Close button", true);
	}
	
	public void save() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_BENCHMARKS_FORM_CLASS, "button",
				"text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(5);
		Reporter.log("Click Save button", true);
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", ADD_BENCHMARKS_FORM_CLASS, "button", "text()",
				"Close", true, true).click();
		//waitForMaskDisappear();
		waitForExtJSAjaxComplete(5);
		Reporter.log("Click Close button", true);
	}
	
	public void changeTab(String tabName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"li", "@id", tabName+"-tab", "span", "@class", "x-tab-strip-text", true, true).click();
		Reporter.log("change tab to "+tabName, true);
	}
	
	public boolean isRowInGridChanelPresent(String gridValue) {
		try {
			McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-panel channels-tab x-panel-noborder')]//div[text()='"+gridValue+"']");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickOnTab(String tabName)
	{
		driver
		.findElement(
				By.xpath("//div[contains(@class, '"+ADD_BENCHMARKS_FORM_CLASS+"')]//ul[contains(@class, 'x-tab-strip')]//span[text()='"+tabName+"']")).click();
		Reporter.log("Click on "+tabName+" tab", true);
	}
	
	public void clickButtonOnDialog(String buttonName)
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_BENCHMARKS_FORM_CLASS, "button", "text()", buttonName, true, true).click();
		Reporter.log("Click on "+buttonName+" button on Benchmark dialog", true);
	}
	
	/////////////////////////////////FormValidation///////////////////////////////////
	
	 public void clearField(String fieldNameTag, String fieldName) {
		WebElement field =  driver.findElement
				(By.xpath("(//div[contains(@class, '"+ADD_BENCHMARKS_FORM_CLASS+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
		field.click();
		field.clear();
		Reporter.log("Clear "+fieldName+" field", true);
	 }
	 
	 public boolean checkFormSaved(){
		 try {
			 String text =  driver.findElement(
						By.xpath("//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//span[contains(@class,'window-header')]")).getText();
			 if(text.contains("Benchmark")){ 
				 return true; }
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			return false;
	}
	 
	 public boolean reopenBenchmarksForm(){
			
		if (!checkFormSaved()) {
			 
			clickAddButton(BENCHMARKS_GRID_CLASS);
		     
		    waitForExtJSAjaxComplete(5);

		    return false;
		 }
		else {
			 
			close();
			 
			waitForExtJSAjaxComplete(5);
			 
			clickOnDialogButton("Yes");
			 
			waitForExtJSAjaxComplete(5);
		     
			clickAddButton(BENCHMARKS_GRID_CLASS);
		     
		    waitForExtJSAjaxComplete(5);
		     
			return true;
		 }
	 }
	 
	 public void clickOnHeading() {
		 try {
			 driver.findElement(
						By.xpath("//div[contains(@class,'"+ADD_BENCHMARKS_FORM_CLASS+"')]//span[contains(@class,'window-header') and contains(text(), 'Benchmark')]")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	 }
	 
	 public boolean reopenBenchmarksFormAndNavigateToEnergyTab(String reference){
			
			if (!checkFormSaved()) {
				
				Grid.checkRowInGriByTextValueAndClick(driver, reference);
				
				waitForExtJSAjaxComplete(5);
				 
				clickEditButton(BENCHMARKS_GRID_CLASS);
			     
			    waitForExtJSAjaxComplete(5);
			    
			    clickOnTab("Energy");
			    
			    return false;
			 }
			else {
				 
				close();
				 
				waitForExtJSAjaxComplete(5);
				
				try {
					clickOnDialogButton("Yes");
				} catch (Exception e) {	}
								 
				waitForExtJSAjaxComplete(5);
			     
				Grid.checkRowInGriByTextValueAndClick(driver, reference);
				
				waitForExtJSAjaxComplete(5);
				 
				clickEditButton(BENCHMARKS_GRID_CLASS);
			     
			    waitForExtJSAjaxComplete(5);
			    
			    clickOnTab("Energy");
			    
				return true;
			 }
		 }
	 
	 public String incrementCode(String code) {
		 codeIncrement+=1;
		 return code+Integer.toString(codeIncrement);
	 }
	 
	 public String incrementRef(String reference) {
		 referenceIncrement+=1;
		 return reference+Integer.toString(referenceIncrement);
	 }
	 
	 public String incrementYear(String year) {
		 yearIncrement+=1;
		 return year+Integer.toString(yearIncrement);
	 }
	 
		public boolean checkInputReadOnly(String elementName){	
			try {
				driver.findElement(By.xpath("//div[contains(@class, '"+ADD_BENCHMARKS_FORM_CLASS+"')]//input[@name='" + elementName + "']//..//input[contains(@class, 'readonly')]"));
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}
	
		public static boolean checkMessageInvalidFormCustomized(WebDriver webDriver, String className, List<String> keyWordsList) {
			String errorMessage = null;
			
			try {
				errorMessage = webDriver.findElement(
						By.xpath("//div[contains(@class,'"+className+"')]//div[contains(@class,'bg-error')]")).getAttribute("textContent");
				System.out.println(errorMessage);
			} catch (Exception e) {
				Reporter.log("Can't find error message bar", true);
				return false;
			}
			for(String keyWord : keyWordsList) {
				if(!errorMessage.contains(keyWord)) {
					Reporter.log(keyWord+" - key word was not found in error message text", true);
					return false;
				}
				else 
					Reporter.log(keyWord+" - key word was found in error message text", true);
			}
			return true;
	}
		
		/**
		 * Helper method to click on Weather Station Lookup
		 */
		public void clickLookUpWeatherStation(String attributeValue) {
			WebElement element = driver.findElement(By.xpath("//div[contains(@class,'"+attributeValue+"')]//div[contains(@class, 'x-form-field-trigger-wrap')]//input//..//..//button"));
			element.click();
		}
		
		/**
		 *  Helper method to get column number in Grid 
		 */
		public int getGridColumnNumber(String attribute, String attributeValue, String columnName){
			
			String columnClass = McsElement.getElementByXpath(driver,"(//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')][last()])").getAttribute("class");
			String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
				
			return Integer.parseInt(columnNumber);
		}
		
		public void clickOnTableCell(String xpathAttribute, String rowNum, String columnName, String value){
			
			int columnNo = getGridColumnNumber("@class", "x-grid-with-col-lines", columnName);
			
			Reporter.log("Column No is "+columnNo ,true);
			
			String xpath = xpathAttribute+"//div[@class='x-grid3-body']//div["+rowNum+"]//td[contains(@class, 'x-grid3-td-"+columnNo+"')]/div";
			
			WebElement ele = McsElement.getElementByXpath(driver, xpath);//.click();
			
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			
			setGridLineTextField(xpathAttribute, value);
		}
		
		public void setGridLineTextField(String xpathAttribute, String value){

			 String xpath = xpathAttribute+"//div[contains(@class,'x-grid-editor') and not(contains(@style,'visibility: hidden'))]//input[@type='text']";

			 WebElement textField = McsElement.getElementByXpath(driver, xpath);
			 
			 textField.clear();

			 textField.sendKeys(value);

			 textField.sendKeys(Keys.ENTER);
		 }
		
		/**
		 * Helper method to find Red border based on tabs
		 * @param webDriver
		 * @param className
		 * @param labelName
		 * @return
		 */
		public static boolean checkInvalidRedBorderInputFieldWOName(String tabName, String name) {
			if (driver.findElement(By.xpath("(//span[contains(@class,'x-tab-strip-text') and text()='"+tabName+"']/ancestor::div[@class='x-tab-strip-wrap']/../following-sibling::div/div/div[not(contains(@class,'x-hide-display'))]//input[@name='"+name+"']//..//input)[last()]"))
					.getAttribute("class").contains("x-form-invalid")) {
				return true;
			} else {
				return false;
			}
			
		}	
		
		
		public void clickOnTableCell(String tabName, String rowNum, String columnNo){
			
			String xpath = tabName+"//div[@class='x-grid3-body']//div["+rowNum+"]//td[contains(@class,'x-grid3-td-"+columnNo+"')]";
			
			WebElement ele = driver.findElement(By.xpath(xpath));
			
			//((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			ele.click();
			
			Reporter.log("Clicked on Table Cell", true);
		}
		
		 public void setGridLineTextField(String value){

			 String xpath = XPATH_NEW_TRANSACTION_LINES_GRID_EDITOR+"//div[contains(@class,'x-grid-editor') and not(contains(@style,'visibility: hidden'))]//input[@type='text']";

			 WebElement textField = driver.findElement(By.xpath(xpath));
			 
			 textField.clear();

			 textField.sendKeys(value);

			 waitForExtJSAjaxComplete(20);
			 
			 textField.sendKeys(Keys.ENTER);
			 
			 waitForExtJSAjaxComplete(25);
			 
			 Reporter.log("Entered Grid Line Value", true);
		 }
		 
		 /**
		 *  Helper method to get column number in Grid 
		 */
		public String getGridColumnNumber(String tabXPATH, String columnName){
			
			String columnClass = driver.findElement(By.xpath(tabXPATH+"//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()]")).getAttribute("class");
			String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
				
			return columnNumber;
		}
		
		public void setAverageForExternalBenhcmark(String tabName, String rowNum,  String value){
			
			String ColNo = getGridColumnNumber(tabName, "Average");
			
			clickOnTableCell(tabName, rowNum, ColNo);
			
			waitForExtJSAjaxComplete(20);
			
			clickOnTableCell(tabName, rowNum, ColNo);
			
			waitForExtJSAjaxComplete(20);
			
			setGridLineTextField(value);
		}
		
		public void setEnergyObjectsForExternalBenhcmark(String tabName, String rowNum,  String value){
			
			String ColNo = getGridColumnNumber(tabName, "of Energy Objects");
			
			clickOnTableCell(tabName, rowNum, ColNo);
			
			waitForExtJSAjaxComplete(20);
			
			/*clickOnTableCell(tabName, rowNum, ColNo);
			
			waitForExtJSAjaxComplete(20);*/
			
			setGridLineTextField(value);
		}
		
		public void setAverageForExternalBenhcmarkWater(String tabName, String rowNum,  String value){
			
			String ColNo = getGridColumnNumber(tabName, "Average");
			
			clickOnTableCell(tabName, rowNum, ColNo);
			
			waitForExtJSAjaxComplete(20);
			
			setGridLineTextField(value);
		}
		
		
}
