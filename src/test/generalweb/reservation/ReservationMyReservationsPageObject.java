package test.generalweb.reservation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.framework.webelement.McsElement;

public class ReservationMyReservationsPageObject extends
ReservationPageObject {
	
	
	protected final String MYRESERVATIONS_CLASS_XPATH = "//div[contains(@class,'myreservations')]";
	
	/**
	 * Helper method to set today date 
	 * @param value
	 */
	public void setTodayDateInMyReservation(String value)
	
	{
		String xpath= MYRESERVATIONS_CLASS_XPATH+"//input[@type='text' and contains(@name,'startdt')]";
		WebElement dateField = McsElement.getElementByXpath(driver, xpath);

		dateField.clear();
		
		dateField.sendKeys(value);

		dateField.sendKeys(Keys.ENTER);
	
	}
	
	/**
	 * Helper method to set future date
	 * @param value
	 */
	public void setFutureDateMyReservation(String value){
		
		String xpath= MYRESERVATIONS_CLASS_XPATH+"//input[@type='text' and contains(@name,'enddt')]";
		WebElement dateField = McsElement.getElementByXpath(driver, xpath);

		dateField.clear();
		
		dateField.sendKeys(value);

		dateField.sendKeys(Keys.ENTER);
	}
	
	/**
	 * Helper method to set subject 0r ID
	 * @param text
	 */
	public void setSubjectOrID(String text)
	{
		String xpath= MYRESERVATIONS_CLASS_XPATH+"//span[text()='ID or Subject']/../../../../..//input[@type='text']";
		WebElement dateField = McsElement.getElementByXpath(driver, xpath);

		dateField.clear();
		
		dateField.sendKeys(text);

		dateField.sendKeys(Keys.ENTER);
	}
	
	/**
	 * Helper method to click Search button
	 */
	public void clickSearchButton()
	{
		String xpath=MYRESERVATIONS_CLASS_XPATH+"//button[contains(@class,'icon-search-large')]";
		WebElement dateField = driver.findElement(By.xpath(xpath));

		dateField.click();
		
	}
	/**
	 * Helper method to click on LookUp button and select the value
	 * @param region 
	 */
	
	 public void setRegionMyReservation(String region){
		  WebDriverWait wait = new WebDriverWait(driver, 30);

		  wait.until(ExpectedConditions.elementToBeClickable(By
		    .xpath(MYRESERVATIONS_CLASS_XPATH+"//span[contains(text(),'Region')]//ancestor::div[contains(@class,'x-btn-group')]//..//button")));

		  clickLookupButtonOfRegionInMyReservations();

		  waitForExtJSAjaxComplete(15);

		  setValueGridLookupWithFiltersWithScrollInToView("@class", "x-resizable", region,"Reference");

		 }
	 /**
	  *Helper method to click on Look Up button when input doesn't have @name attribute
	  */

	 public void clickLookupButtonOfRegionInMyReservations()
	 {
	  String xpath=MYRESERVATIONS_CLASS_XPATH+"//span[contains(text(),'Region')]//ancestor::div[contains(@class,'x-btn-group')]//..//button";
	  McsElement.getElementByXpath(driver, xpath).click();
	 }
	 
	 /**
	  * Helper method to get Region in MyReservations
	  * @return
	  */
	 public String getRegionInMyReservation(){
		 return driver.findElement(By.xpath("//span[contains(text(),'Region')]//ancestor::div[contains(@class,'x-btn-group')]//input")).getAttribute("value");
	 }
	 
	 /**
	  * Helper method to check whether reservation is present with id or not
	  */
	 public boolean isReservationWithIDPresentInMyReservations(String id){
		 String xpath = "//div[contains(@class,'x-grid-group') and contains(@id,'"+id+"')]";
		 return McsElement.isElementPresent(driver, xpath);
	 }

	 /**
	  * Helper method to verify reservations based on ID
	  */ 
	 public boolean	verifyReservationsInMyReservationBasedOnIDs(List<String> ids){
		 boolean isReservationPresent = false;
		 for(String id: ids){
			 isReservationPresent = isReservationWithIDPresentInMyReservations(id);
			 if(!isReservationPresent){
				 Reporter.log("Reservation with ID "+id+" is not present in My reservations screen", true);
				 break;
			 }
		 }
		 return isReservationPresent;
	 }

}
