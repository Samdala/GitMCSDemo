package test.energy.navigator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class EnergyAnalysisPageObject extends NavigatorPageObject {


	protected final String ENERGY_ANALYSIS = "x-panel-body x-panel-body-noheader x-panel-body-noborder x-form";
	protected final String CHART_DATA_ANALYSIS = "x-tab-panel x-tab-panel-noborder x-box-item";

	
	public void ClickEditButton() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "fieldset", "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("click edit button", true);
	}
	
	public boolean isDataLabelPresent(String labelMessage) {
		try {
			driver.findElement(By.xpath("//form[contains(@class,'" + ENERGY_ANALYSIS + "')]//label[contains(text(),'" + labelMessage + "')]"));
			//Reporter.log("click edit button " + textLabel, true);
			return true;
		} catch (Exception e) {
		return false;
		}
	}
	
	public void clickLookup(String formClass, String inputName) {
		Timer timer = new Timer().start();
		int xwindowNumber=0;
		
		try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size();
		}
		finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}

		waitFocusAndClick("//form[contains(@class,'"
				+ ENERGY_ANALYSIS + "')]//input[contains(@name,'"
				+ inputName + "')]//..//..//button");
		
		waitForExtJSAjaxComplete(10);
		waitForExtJSAjaxComplete(10);
		
		try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size() == xwindowNumber)
				{waitAndClick("(//img[contains(@src,'library/lookup2/images/details.png')]) [last()]");}
		}

		 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}

		Reporter.log(inputName + " lookup was clicked"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void setMetric(String itemValue) {
		String elementId = driver.findElement(By
				.xpath("//form[contains(@class,'" + ENERGY_ANALYSIS + "')]//input[@name='metric']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, itemValue);
		Reporter.log("Set Metric - "+itemValue, true);
	}
	
	public void setDimension(String itemValue) {
		String elementId = driver.findElement(By
				.xpath("//form[contains(@class,'" + ENERGY_ANALYSIS + "')]//input[@name='dimension']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, itemValue);
		Reporter.log("Set Dimension - "+itemValue, true);
	}
	
	public void setCommodityClass(String itemValue) {
		String elementId = driver.findElement(By
				.xpath("//form[contains(@class,'" + ENERGY_ANALYSIS + "')]//input[@name='commodityClass']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, itemValue);
		Reporter.log("Set CommodityClass - "+itemValue, true);
	}
	
	public void setCommodityEndUse(String itemValue) {
		String elementId = driver.findElement(By
				.xpath("//form[contains(@class,'" + ENERGY_ANALYSIS + "')]//input[@name='commodityClassEndUse']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, itemValue);
		Reporter.log("Set CommodityEndUse - "+itemValue, true);
	}
	
	public void setCommodityUOM(String itemValue) {
		clickLookup(ENERGY_ANALYSIS, "commodityClassUom");
		setValueGridLookup(itemValue);
		Reporter.log("Set CommodityUOM - "+itemValue, true);
	}
	
	public void setParameter(String itemValue) {
		String elementId = driver.findElement(By
				.xpath("//form[contains(@class,'" + ENERGY_ANALYSIS + "')]//input[@name='denominator']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, itemValue);
		Reporter.log("Set Parameter denominator - "+itemValue, true);
	}
	
	public void setDenominatorUom(String itemValue) {
		clickLookup(ENERGY_ANALYSIS, "denominatorUom");
		setValueGridLookupWithFilters(itemValue, "Code");
		Reporter.log("Set CommodityUOM - "+itemValue, true);
	}
	
	public void setReportingInterval(String itemValue) {
		String elementId = driver.findElement(By
				.xpath("//form[contains(@class,'" + ENERGY_ANALYSIS + "')]//input[@name='reportingInterval']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, itemValue);
		Reporter.log("Set ReportingInterval - "+itemValue, true);
	}
	
	public void setDateRange(String itemValue) {
		WebElement dateRangeField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"form", "@class", ENERGY_ANALYSIS, "input", "@name",
				"dateRange", true, true);
		dateRangeField.click();
		dateRangeField.clear();
		dateRangeField.sendKeys(itemValue);
		dateRangeField.click();
		//WebElement window = McsElement.getElementByPartAttributeValueAndParentElement(driver,
		//		"form", "@class", ENERGY_ANALYSIS, "div", "@class",
		//		"x-window-mr", true, true);
		waitForExtJSAjaxComplete(20);
		//window.click();
		//waitForExtJSAjaxComplete(20);
		Reporter.log("set DateRange " + itemValue, true); 
	}

	public void clickRunAnalysis() {
		WebElement runAnalysis = driver.findElement(By
				.xpath("//form[contains(@class,'" + ENERGY_ANALYSIS + "')]//button[text()='Run Analysis']"));
		
		//javaScriptClick(runAnalysis);
		runAnalysis.click();
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Button Run Analysis click", true); 
	}
	
	public void clickNormalizeCDD() {
		driver.findElement(By
				.xpath("//div[contains(@class,'" + CHART_DATA_ANALYSIS + "')]//button[contains(@class,'icon-report-normalize-cdd')]")).click();
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Button NormalizeCDD clicked", true); 
	}
	
	public void clickNormalizeHDD() {
		driver.findElement(By
				.xpath("//div[contains(@class,'" + CHART_DATA_ANALYSIS + "')]//button[contains(@class,'icon-report-normalize-hdd')]")).click();
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Button NormalizeHDD clicked", true); 
	}
	
	public boolean isDataRowValuesPresent(String values) {
		return Grid.isRowInGridPresentLike(driver, values, "@class", CHART_DATA_ANALYSIS);
	}
	
	public void setCase (String metric, String dimension, String commodityClass, String commmodityEndUse, 
			String comodityUOM, String parameter, String denominatorUOM,  String reportingInterval, String dateRange) {
		
		if (metric != "") setMetric(metric);
		
		waitForExtJSAjaxComplete(20);
		
		if (reportingInterval != "") setReportingInterval(reportingInterval);
		
		waitForExtJSAjaxComplete(20);
		
		if (dateRange != "") setDateRange(dateRange);
		
		waitForExtJSAjaxComplete(20);
		
		if (dimension != "") setDimension(dimension);
		
		waitForExtJSAjaxComplete(20);
		
		if (commodityClass != "") setCommodityClass(commodityClass);
		
		waitForExtJSAjaxComplete(20);
		
		if (commmodityEndUse != "") setCommodityEndUse(commmodityEndUse);
		
		waitForExtJSAjaxComplete(20);
		
		if (comodityUOM != "") setCommodityUOM(comodityUOM);
		
		waitForExtJSAjaxComplete(20);
		
		if (parameter != "") setParameter(parameter);
		
		waitForExtJSAjaxComplete(20);
		
		if (denominatorUOM != "") setDenominatorUom(denominatorUOM);
		
		waitForExtJSAjaxComplete(20);
		
	}
	
	/**
	 * Helper method to get Values from Commodity Class Drop down
	 */
	public List<String> getEquivalencyValues(String attributeValue){
		
		WebElement commodityClassDropDown = driver.findElement(By.xpath("//form[contains(@class,'"+ENERGY_ANALYSIS+"')]//input[@name='"+attributeValue+"']"));
			
		commodityClassDropDown.click();
		
		List<WebElement> values = null;
		
		values = driver.findElements(By.xpath("//div[contains(@class, 'x-combo-list') and (contains(@style, 'visibility: visible'))]//div[contains(@class,'x-combo-list-item')]/div"));
					
		ArrayList<String> lsValues = new ArrayList<String>(); 
				
		for(int i=0; i<values.size(); i++){
					
		String valueText = values.get(i).getText().trim();
					
		lsValues.add(valueText);
		
		System.out.println(valueText);
		}
		
		return lsValues;
	}
	
}