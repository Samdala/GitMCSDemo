package test.generalweb.today;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class TodayPageObject extends AbstractMcsTestSuite{

	protected final String SUBJECT_XPATH = "//div[contains(@class,'x-gadget')]//div[contains(@class,'helpdesk')]//input[@name='NEW_REFERENCE']";

	protected final String PRIORITY_XPATH="//div[contains(@class,'x-gadget')]//div[contains(@class,'helpdesk')]//input[@name='NEW_PRIORITY']/..//input[contains(@class,'x-form')]";

	protected final String BOOKCALL_XPATH = "//div[contains(@class,'x-gadget')]//div[contains(@class,'helpdesk')]//button[contains(text(),'Book Call')]";

	protected final String TODAY_WIN_XPATH = "//div[contains(@class,'today ')]";

	/**
	 * Helper method to open the List of Gadgets
	 */
	public void openTheListOfGadgets(){
		driver.findElement(By.xpath(""+TODAY_WIN_XPATH+"//button[contains(text(),'Add more..')]")).click();
		Reporter.log("List Of Gadgets Buttons is clicked",true);
	}


	/**
	 * Helper method to check the availability of Gadget
	 * @param text gadget name
	 * @return
	 */
	public boolean isGadgetPresentListOfGadgets(String text){
		return McsElement.isElementDisplayed(driver,"//div[@id='"+getXWindowId("Add a new Gadget")+"']//span[contains(text(),'"+text+"')]");
	}


	/**
	 * Helper method to check the presence of Gadget in Today window
	 * @param text gadget name
	 * @return
	 */
	public boolean isGadgetPresentInTodaysWindow(String text){
		return McsElement.isElementDisplayed(driver,""+TODAY_WIN_XPATH+"//span[contains(text(),'"+text+"')]");
	}


	/**
	 * Helper method to remove Gadget from Today window
	 * @param gadgetName
	 * @return
	 */
	public boolean removeGadgetFromTodayWindow(String gadgetName){

		if(isGadgetPresentInTodaysWindow(gadgetName)){
			driver.findElement(By.xpath("//div[@id='"+getGadgetID(gadgetName)+"']//div[contains(@class,'x-tool-gear')]")).click();

			waitForExtJSAjaxComplete(20);

			DropDown.selectMenuItemFromAlreadyPopulatedList(driver,"Remove");

			Reporter.log("Gadget is removed",true);

			removeGadgetFromTodayWindow(gadgetName);


			return true;
		}
		else{
			Reporter.log("No such Gadget is found",true);

			return false;
		}
	}


	/**
	 * Helper method to add the Gadget to Today Tab 
	 * @param text gadget name
	 */
	public void addGadgetToTodayTab(String text){
		if(isGadgetPresentListOfGadgets(text)){
			driver.findElement(By.xpath("//div[@id='"+getXWindowId("Add a new Gadget")+"']//span[contains(text(),'"+text+"')]")).click();
			waitForExtJSAjaxComplete(20);
			driver.findElement(By.xpath("//div[(@id='"+getXWindowId("Add a new Gadget")+"') and contains(@style,'visibility: visible')]//button[text()='Add']")).click();
		}
	}

	/**
	 * Helper method to get list of call Templates in BookCall Gadget
	 * @return list of call templates
	 */
	public List<String> getListOfTemplatesInGadget(){
		List<String> templates = new ArrayList<String>();
		List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'x-gadget')]//div[contains(@class,'helpdesk-newcall')]//div[@class='tplLabel']"));
		for(WebElement template : ele)
			templates.add(template.getText());
		return templates;
	}


	/**
	 * Helper method to filter the call template by reference 
	 * @param callTempRef template reference 
	 */
	public void filterTemplateByReference(String callTempRef){
		String xpath = "//div[contains(@class,'x-gadget')]//div[contains(@class,'helpdesk-newcall')]//input[@name='treesearch']";
		WebElement treesearch = driver.findElement(By.xpath(xpath));
		treesearch.click();
		treesearch.clear();
		treesearch.sendKeys(callTempRef);
		waitForExtJSAjaxComplete(20);
		driver.findElement(By.xpath("//div[contains(@class,'x-gadget')]//div[contains(@class,'helpdesk-newcall')]//button[contains(@class,'icon-binocular')]")).click();

	}


	/**
	 * Helper method to expand call templates
	 * @param header window header 
	 */
	public void expandCallTemplates(String header){
		String xpath = "(//span[contains(text(),'"+header+"')]/../..)//div[contains(@class,'x-tool-plus')]";
		if(McsElement.isElementDisplayed(driver,xpath)){
			waitForExtJSAjaxComplete(20);
			driver.findElement(By.xpath(xpath)).click();
			Reporter.log("Call Templates are expanded",true);
		}
		else
			Reporter.log("Call Template is already expanded",true);

	}


	/**
	 * Helper method to collapse call templates
	 * @param header window header 
	 */
	public void collapseCallTemplates(String header){

		String xpath = "(//span[contains(text(),'"+header+"')]/../..)//div[contains(@class,'x-tool-minus')]";
		if(McsElement.isElementDisplayed(driver,xpath)){
			waitForExtJSAjaxComplete(20);
			driver.findElement(By.xpath(xpath)).click();
			Reporter.log("Call Templates are collapsed",true);
		}
		else
			Reporter.log("Call Template is already collapsed",true);
	}


	/**
	 * Helper method to toggle or maximize the gadget window
	 * @param text gadget name
	 * @param toolClass
	 */
	public void toggleOrMaximizeGadgetWindow(String text, String toolClass){

		if(isGadgetPresentInTodaysWindow(text)){
			driver.findElement(By.xpath(""+TODAY_WIN_XPATH+"//span[contains(text(),'"+text+"')]//ancestor::div[contains(@class,'x-gadget')]//div[contains(@class,'"+toolClass+"')]")).click();

			waitForExtJSAjaxComplete(20);

			Reporter.log("Window is maximized ",true);
		}
	}


	/**
	 * Helper method to check whether call Templates are collapsed are not
	 * @param header window title
	 * @return
	 */
	public boolean areCallTemplatesCollapsed(String header){
		String xpath = "(//span[contains(text(),'"+header+"')]/../..)//div[contains(@class,'x-tool-plus')]";
		return  McsElement.isElementDisplayed(driver,xpath);
	}


	/**
	 * Helper method to click on selected call Template
	 * @param template Template reference 
	 */
	public void clickCallTemplate(String template){
		String xpath = "//div[contains(@class,'x-gadget')]//div[contains(@class,'helpdesk-newcall')]//div[contains(text(),'"+template+"')]";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
		Reporter.log("Click call template <br>", true);
	}


	/**
	 * Helper method to set subject in Reference field
	 * @param reference
	 */
	public void setSubject(String reference){

		WebElement eleTemplate = driver.findElement(By.xpath(SUBJECT_XPATH));
		eleTemplate.click();
		eleTemplate.clear();
		eleTemplate.sendKeys(reference);
		eleTemplate.sendKeys(Keys.TAB);
	}


	/**
	 *  Helper method to set priority in call template
	 * @param priority
	 */
	public void setPriority(String priority){
		DropDown.setExtJsComboValue(driver, driver.findElement(By.xpath(PRIORITY_XPATH)).getAttribute("id"), priority);
	}


	/**
	 * Helper method to set nature in call template
	 * @param nature
	 */
	public void setNature(String nature) {

		clickLookup("@class","x-gadget", "NEW_NATURE","Select a Nature");

		waitForExtJSAjaxComplete(25);

		driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[contains(text(),'Tree')]")).click();

		setValueTreeLookup(new String[]{nature});

	}


	/**
	 * Helper method to click on Book Call
	 */
	public void clickBookCall(){
		clickXPath(BOOKCALL_XPATH);
		Reporter.log("Book call was clicked <br>", true);
	}


	/**
	 * Helper method to get Infobar Message
	 */
	public String getInfobarMessage(){
		return driver.findElement(By.xpath("//div[contains(@class,'x-gadget')]//div[contains(@class,'infobar bg-success')]")).getText();

	}


	/**
	 * Helper method to get GadgetWindow Id
	 * @param gadgetName
	 * @return
	 */
	public String getGadgetID(String gadgetName){
		return driver.findElement(By.xpath(""+TODAY_WIN_XPATH+"//span[@class='x-panel-header-text' and text()='"+gadgetName+"']/ancestor::div[contains(@class,'x-gadget')]")).getAttribute("id");

	}

}