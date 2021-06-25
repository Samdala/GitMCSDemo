package test.generalweb.helpdesk;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.webelement.McsElement;


public class HelpDeskNavigationPageObject extends AbstractMcsTestSuite {
	

	
	public String getCallTemplateNameFromPageHeader() {
		return McsElement.getElementByXpath(driver, "//div[contains(@class, 'general')]//span[contains(@class, 'x-panel-header-text')]").getText();
	}
	
	public boolean verifyButtonPressedPage(String buttonText) {
		try {
			McsElement.getElementByXpath(driver, "//table[contains(@class, 'pressed')]//em//button[contains(text(), '" + buttonText + "')]");
			Reporter.log("Button is pressed.", true);
			return true;
		}
		catch(Exception e) {
			Reporter.log("Button isn't pressed.", true);
			return false;
		}
	}
	
	public boolean isColapsedCallTemplatePanel() {
		try {
			driver.findElement(By.xpath("//div[contains(@class, 'collapsed') and (contains(@style,'index') or contains(@style,'INDEX'))]//span[contains(text(), 'Call Templates')]"));
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean isInfoTextPresent(String infoText) {
		try {
			McsElement.getElementByXpath(driver, "//label[contains(text(), '" + infoText + "')]");
			return true;
		}
		catch(Exception e) {
			return false;
		}
	} 
	
	public boolean isWarningTextDisplayed(String warningText) {
		try {
			McsElement.getElementByXpath(driver, "//span[contains(text(), '" + warningText + "')]");
			return true;
		}
		catch(Exception e) {
			return false;
		}
	} 
	
	public boolean isCallTeamplatePresent(String template) {
		try {
			McsElement.getElementByXpath(driver, "//div[contains(@class,'helpdesk')]//div[contains(text(),'"+template+"')]");
			Reporter.log("Call template is present.", true);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	 /**
	  * Select the First row in the grid
	  */
	 public void selectFirstRowInGrid(){
		 driver.findElement(By.xpath("//div[contains(@class, 'x-grid3-viewport')]//div[contains(@class, 'x-grid3-body')]//div[contains(@class,'row-first')]")).click();
			waitForExtJSAjaxComplete(20);	 
	 }
	 
	 /**
	  * Get Call ID
	 * @return 
	  */
	 public String getCallId(){
		 
		 String id = driver.findElement(By.xpath("//div[contains(@class,'hdwo-details')]//div[starts-with(text(),'Call')]")).getText();
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);
			String [] callId = id.split(" ");
			waitForExtJSAjaxComplete(20);
			return callId[1];
	 }
	 
	 /**
	  * Checking whether call is opened or not
	  * @param callID
	  * @return
	  */
	 public boolean isCallDetailsWindowOpen(String callID)
	 {
		 try{
			 waitForExtJSAjaxComplete(20);
			  driver.findElement(By.xpath("//div[contains(@class,'resizable-pinned') and contains(@style,'visibility: visible')]//div[contains(@class,'hdwo-details')]//div[starts-with(text(),'Call "+callID+"')]"));
			 waitForExtJSAjaxComplete(20);
			 return true;
			 }
		 catch(Exception E)
		 {
			return false;
		 }
		 
	 }
}