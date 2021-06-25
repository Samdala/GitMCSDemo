package test.generalweb.moveproject;





import java.util.HashMap;
import java.util.Map;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.McsElement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class ProjectTemplatesPageObject extends AbstractMcsTestSuite {

	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";
	
	protected final String FORM_TEMPLATE_NAME = "Call Template";
	
	protected final String TEMPLATE_PANEL = "Templates";

	public void goToAdministration() {
		driver.get(configuration.getApplicationUrl()
				+ "/frame.php?relay=ADM_SETTINGS");
	};

	public void expandModuleSettings() {
		expandNode("div","@id",getXPanelId("Administration"),"Module Settings");
		}
	
	public void clickAdministration() {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "mcs-tb-glossy-strong", "button", "text()",
				"Administration", true, true).click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click Administration"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void clickProjectTemplate() {
		Timer timer = new Timer().start();
		WebElement callTemplate = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", getXPanelId("Administration"), "span", "text()",
				"Move Project Templates", true, true);
		javaScriptFocus(callTemplate);
		callTemplate.click();
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click project template"+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickAddTemplate() {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId(TEMPLATE_PANEL),"button", "@class",
				"icon-template-add", true, true).click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add template"+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickDeleteTemplate() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId(TEMPLATE_PANEL),"button", "@class",
				"x-btn-text icon-template-delete", true, true).click();
		Reporter.log("Click delete template", true);
	}

	public void clickAddGroupTemplate() {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId(TEMPLATE_PANEL),"button", "@class",
				"icon-templategroup-add", true, true).click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add group template"+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickDeleteGroupTemplate() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId(TEMPLATE_PANEL),"button", "@class",
				"icon-templategroup-delete", true, true).click();
		Reporter.log("Click delete group template", true);
	}

	
	
	
	public void clickActiv() {
		//McsElement.getElementByXpath(driver, "//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//input[contains(@class,'x-form-checkbox')]").click();
		
		WebElement checkBox = McsElement.getElementByXpath(driver, "//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//input[contains(@class,'x-form-checkbox')]");
		
		if(!checkBox.isSelected()){
			checkBox.click();
		}
	}
	

	public void unCheckActive() {
		
		WebElement checkBox = McsElement.getElementByXpath(driver, "//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//input[contains(@class,'x-form-checkbox')]");
		
		if(checkBox.isSelected()){
			checkBox.click();
		}
	}
	
	
	public void clickAddAdditionalQuestion(){
		McsElement.getElementByXpath(driver, "//button[contains(text(),'Add Additional Question')]").click();
	}
	

	public void setAddAdditionalQuestion(String question){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]//input[contains(@class,'ext-mb-input')]").click();
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]//input[contains(@class,'ext-mb-input')]").clear();
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]//input[contains(@class,'ext-mb-input')]").sendKeys(question);
	}


	public void setDefaultValue(String field, String question){
		
		waitForExtJSAjaxComplete(20);
		
		String xpath = "//div[contains(@class,'x-panel')]//div[contains(text(),'Default Value')]";
		
		WebElement ele = driver.findElement(By.xpath(xpath));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				
		String columnClass = ele.getAttribute("class");
		
		String columnNumber = columnClass.substring(columnClass.length() - 1);
		
		McsElement.getElementByXpath(driver,"//div[contains(text(),'"+field+"')]/../..//div[contains(@class,'x-grid3-col-"+columnNumber+"')]").click();
		
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByXpath(driver,"//input[contains(@class,'x-form-text x-form-field x-form-focus')]").click();
		
		McsElement.getElementByXpath(driver,"//input[contains(@class,'x-form-text x-form-field x-form-focus')]").clear();
		
		McsElement.getElementByXpath(driver,"//input[contains(@class,'x-form-text x-form-field x-form-focus')]").sendKeys(question);
		
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByXpath(driver,"//div[contains(text(),'"+field+"')]").click();
		
		waitForExtJSAjaxComplete(25);
	
	}
	
	
	public void checkVisible(String FieldName){
		WebElement visible = McsElement.getElementByXpath(driver, "//div[contains(@class,'cell')]//span[contains(text(),'"+FieldName+"')]/../../..//div[contains(@class,'check')]");
		if (!visible.getAttribute("class").contains("check-col-on")){
			visible.click();
		}
	}
	
	
	public boolean getActivState() {
		if (McsElement.getElementByXpath(driver, "//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//input[contains(@class,'x-form-checkbox')]").getAttribute("checked") == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void clickGeneralTab() {
		McsElement.getElementByXpath(driver, "//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//span[contains(text(),'General')]").click();
	}
	
	public void clickFieldsTab() {
		McsElement.getElementByXpath(driver, "//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//span[contains(text(),'Fields')]").click();
	}
	
	public void clickFieldLabelTab() {
		McsElement.getElementByXpath(driver, "//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//span[contains(text(),'Field Labels')]").click();
	}


	public void clickResrictionsTab() {
		McsElement.getElementByXpath(driver, "//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//span[contains(text(),'Restrictions')]").click();
	}


	public void clickAddRestriction() {
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-panel')]//button[text()='Add']").click();
	}

	
	public void setReference(String referenceText) {
		WebElement reference = McsElement.getElementByXpath(driver, "//span[text()='Templates']/../..//label[contains(text(),'Reference:')]//..//input");
				
		reference.clear();
		reference.sendKeys(referenceText);
		Reporter.log("Set reference", true);
	}

	public void setReferenceGroup(String referenceText) {
		WebElement reference = McsElement.getElementByXpath(driver, "//span[text()='Template Group']/../../../../../../../../../../../..//label[contains(text(),'Reference:')]//..//input");
				
		reference.clear();
		reference.sendKeys(referenceText);
		Reporter.log("Set reference group", true);
	}

	public void setLabelDescription(String language, String label, String description) {
		
		String id = driver.findElement(By.xpath("//img[contains(@class,'icon-translations') and contains(@class,'x-panel-inline-icon')]/../..")).getAttribute("id");
		
		String script = "for (var i = 0; i < Ext.getCmp('"+id+"').getStore().data.length; i++)"
				+ "{if (Ext.getCmp('"+id+"').getStore().data.items[i].data.languagecaption=='"+language+"')"
				+ "{Ext.getCmp('"+id+"').getStore().data.items[i].data.label='"+label+"';"
				+ "Ext.getCmp('"+id+"').getStore().data.items[i].data.description='"+description+"';"
				+ "Ext.getCmp('"+id+"').getStore().data.items[i].afterEdit()}}";
						
		((JavascriptExecutor) driver)
		.executeScript(script);
		
		Reporter.log("Set label and description", true);
	}


	public void setEnglishFieldLabel(HashMap<String,String> fieldLanguage) {
		
		String id = driver.findElement(By.xpath("(//div[contains(@class,'x-grid3-hd-inner') and contains(text(),'German')]/ancestor::div[contains(@id,'ext-comp')])[last()]")).getAttribute("id");
		
		for (Map.Entry<String, String> entry : fieldLanguage.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		String script = "for (var i = 0; i < Ext.getCmp('"+id+"').getStore().data.length; i++)"
				+ "{if (Ext.getCmp('"+id+"').getStore().data.items[i].data.field=='"+key+"')"
				+ "{Ext.getCmp('"+id+"').getStore().data.items[i].data.label_en='"+value+"';"
				+ "Ext.getCmp('"+id+"').getStore().data.items[i].afterEdit()}}";
						
		((JavascriptExecutor) driver)
		.executeScript(script);
		}
		Reporter.log("Set english labels and fields", true);
	}

	
	
	
	public String getReference() {
		return McsElement.getElementByXpath(driver, "//span[text()='Templates']/../..//label[contains(text(),'Reference:')]//..//input").getAttribute("value");
		
	}
	
	public String getReferenceGroup() {
		return McsElement.getElementByXpath(driver, "//span[text()='Template Group']/../../../../../../../../../../../..//label[contains(text(),'Reference:')]//..//input").getAttribute("value");
		
	}
	
	public void saveCallTemplate() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[text()='Templates']/../..//span[contains(text(),'Save Changes')]").click();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save call template"+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void saveCallTemplateGroup() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[text()='Template Group']/../../../../../../../../../../../../../../..//span[contains(text(),'Save Changes')]").click();

		waitForExtJSAjaxComplete(20);
		Reporter.log("Save call template group"+ " (" + timer.stop()
		+ "ms)", true);
	}

	/**
	 * Helper method to click on security tab
	 */
	public void clickSecurityTab() {
		McsElement.getElementByXpath(driver, "//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//span[contains(text(),'Security')]").click();
	}

	/**
	 * Helper method to add Group in security tab
	 */
	public void clickAddGroup() {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId(TEMPLATE_PANEL),"button", "@class",
				"icon-account-group", true, true).click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add template"+ " (" + timer.stop()
		+ "ms)", true);
	}

	/**
	 * Helper method to get check box field status
	 * @param fieldName to get status
	 * @return true if checked
	 */
	public boolean getCheckBoxFieldStatus(String fieldName){
		String status =  driver.findElement(By.xpath("//div[contains(@class, 'mcs-tree-navigation')]/following-sibling::div[contains(@class, 'x-panel-body')]//span[contains(text(),'"+fieldName+"')]/../preceding-sibling::input")).getAttribute("checked");
		return (status==null||status.equals(false))?false:true;
	}

	/**
	 * Method allows to check if row with one or some values is present in grid
	 * @param fieldName
	 * @return true if row is present
	 */
	public boolean isRowInSecurityTabPresent(String fieldName){
		String rowXpath = "//div[contains(@class,'admsettings-modulesettings')]//div[contains(@class,' x-panel-body x-border-panel')]//div[@class='x-list-body']//dl[contains(.,'"+fieldName+"')]";
		try {
			driver.findElement(By.xpath(rowXpath));
		} catch (Exception e) {
			Reporter.log("row can not be found in grid: ",true);
			return false;
		}
		Reporter.log("Row with text like " + fieldName  + " was successfully found", true);
		return true;
	}
	
	/**
	 * Helper method to add account in security tab
	 */
	public void clickAddAccount() {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId(TEMPLATE_PANEL),"button", "@class",
				"icon-account", true, true).click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add template"+ " (" + timer.stop()
		+ "ms)", true);
	}
}
