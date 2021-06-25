package test.generalweb.reservation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import test.framework.webelement.McsElement;

public class ReservationBackOfficePageObject extends ReservationsCalendarNewPageObject {
	
	protected final String RESERVATION_DETAILS_WINDOW="//div[contains(@class,'reservations x-resizable-pinned') and contains(@style,'visible')]";
	
	protected final String RESERVATION_BACKOFFICE_EDITWINDOW="//div[contains(@class,'mcsreservationedit-backoffice')]";
    
   
   
    /**
     * Helper method to click on tab
     * @param tab
     */
    public void clickOnButton(String tab)
    {
    	String xpath=RESERVATION_DETAILS_WINDOW+"//span[text()='"+tab+"']";
    	
    	driver.findElement(By.xpath(xpath)).click();
    }
    
    /**
	 *Helper method to get date
	 * @return value
	 */
	
	public String getDateInReservationDetailsWindow(){
		
		return McsElement.getElementByXpath(driver,RESERVATION_DETAILS_WINDOW+"//td[contains(@class,'datetime-date')]//div[contains(@class,'x-form-field') and not (contains(@class,'x-item-disabled'))]//input").getAttribute("value");
		
	}
    
    /**
	 * Helper method to click on tabs in reservation details window
	 * @param tabText text of tab to be clicked
	 */
	public boolean isTabPresent(String tabText){
		
		String xpath=RESERVATION_DETAILS_WINDOW+"//span[contains(@class,'x-tab-strip-text') and contains(text(),'"+tabText+"')]";
		return McsElement.isElementPresent(driver, xpath);
		
	}
	
	/**
	 * Helper method to get subject
	 * @return
	 */
	public String getGeneralSubjectInReservationDetails(){
		return driver.findElement(By.xpath(RESERVATION_DETAILS_WINDOW+"//textarea[contains(@name,'subject')]")).getAttribute("value");
	}

	/**
	 * Helper method to set subject
	 * @param remark
	 */
	public void setGeneralsubjectInReservationDetails(String remark){
		driver.findElement(By.xpath(RESERVATION_DETAILS_WINDOW+"//textarea[contains(@name,'subject')]")).click();
		driver.findElement(By.xpath(RESERVATION_DETAILS_WINDOW+"//textarea[contains(@name,'subject')]")).clear();
		driver.findElement(By.xpath(RESERVATION_DETAILS_WINDOW+"//textarea[contains(@name,'subject')]")).sendKeys(remark);
		waitForExtJSAjaxComplete(5);
	}

	/**
	 *Helper method to verify button disabled 
	 */
	public boolean isButtonDisabled(String btn)
	{
		String xpath=RESERVATION_DETAILS_WINDOW+"//button[text()='"+btn+"']/ancestor::table[contains(@class,'x-btn')]";
		WebElement element=driver.findElement(By.xpath(xpath));
		String text=element.getAttribute("class");
		if(text.contains("x-item-disabled"))
		{
		return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Helper method to click cancel
	 */
	public void clickCancelButtonInReservationDetails() {
		
		String xpath=RESERVATION_DETAILS_WINDOW+"//button[text()='Cancel']";
		
		WebElement element=driver.findElement(By.xpath(xpath));
		element.click();
	}
	
	/**
	 * Helper method to cancel reservation details window
	 */
	public void clickCloseReservationDetailswindow()
	{
		String xpath="//div[contains(@class,'reservations x-resizable-pinned') and contains(@style,'visible')]//span[contains(text(),'Reservation')]//preceding-sibling::div[contains(@class,'x-tool-close')]";
		WebElement element=driver.findElement(By.xpath(xpath));
		element.click();
	}
		
	
	/**
	 * Helper method to resize inspection window
	 */
	public void resizeTheReservationDetailsWindow(){
		String xpath = RESERVATION_DETAILS_WINDOW;
		int widthOfXWindow = driver.findElement(By.xpath(xpath)).getSize().getWidth();
		waitForExtJSAjaxComplete(10);

			Actions action = new Actions(driver);
			String startXWindow = RESERVATION_DETAILS_WINDOW+"//div[contains(@class,'x-resizable-handle-south')]";
			WebElement startXWindowElement = driver.findElement(By.xpath(startXWindow));
			action.moveToElement(startXWindowElement).clickAndHold().moveByOffset(widthOfXWindow/15,0).release().perform();
			waitForExtJSAjaxComplete(10);
			//String endXWindow = RESERVATION_DETAILS_WINDOW+"//div[contains(@class,'x-resizable-handle-north')]";
			//WebElement endXWindowElement = driver.findElement(By.xpath(endXWindow));
			//action.moveToElement(endXWindowElement).clickAndHold().moveByOffset(-(widthOfXWindow/15),0).release().perform();
			Reporter.log("Reservation details window is resized",true);
		}
	
	/**
	 * Helper method to check whether fields are disabled in reservation details window
	 * @param field: text of field
	 */
	public boolean isFieldDisabled(String field){
		
		String xpath= RESERVATION_BACKOFFICE_EDITWINDOW +"//label[contains(text(),'"+field+"')]/..//input[contains(@class,'x-form-readonly x-trigger-noedit')]";
		return McsElement.isElementPresent(driver, xpath);
		
	}
	
}


