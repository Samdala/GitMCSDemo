package test.generalweb.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import test.framework.AbstractMcsTestSuite;
import test.framework.webelement.McsElement;

public class AdministrationPageObject extends AbstractMcsTestSuite {

	protected final String MYACCOUNT_CLASS_XPATH = "//div[contains(@class,' accedit')]";
	protected final String MYACCOUNT_EMPDETAILS_SECTION = "//div[contains(@class,' x-panel details-general')]";
	
	
	
	
	
//=========================== Employee Details: Picture===================================================
	
	/**
	 * Helper method to verify default image is displayed or not
	 * @return true:false
	 */
	public boolean isDefaultImgDisplayed()
	{
		String xpath=MYACCOUNT_CLASS_XPATH+MYACCOUNT_EMPDETAILS_SECTION+"//img[contains(@src,'modules/accedit/downloadpicture')]";
		return McsElement.isElementPresent(driver, xpath);	
		
	}
	
	/**
	 * Helper method to verify button is displayed or not
	 * @param name
	 * @return
	 */
	public boolean isChangeButtonDisplayed(String name){
		WebElement element =driver.findElement(By.xpath(MYACCOUNT_CLASS_XPATH+MYACCOUNT_EMPDETAILS_SECTION+"//input[contains(@class,'x-form-file-text')]//following-sibling::input"));
		String btnName=element.getAttribute("value");
		if(btnName.contains(name))
		{
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Helper method to verify button is displayed or not
	 * @param name
	 * @return
	 */
	public boolean isButtonDisplayed(String name){
		String xpath =MYACCOUNT_CLASS_XPATH+MYACCOUNT_EMPDETAILS_SECTION+"//table[contains(@class,'x-btn')]//button[contains(text(),'"+name+"')]";
		return McsElement.isElementPresent(driver, xpath);
	}
	
	/**
	 * Helper method to verify button is displayed or not
	 * @param name
	 * @return
	 */
	public boolean isButtonDisabled(String name){
		String xpath =MYACCOUNT_CLASS_XPATH+MYACCOUNT_EMPDETAILS_SECTION+"//button[contains(text(),'"+name+"')]//ancestor::table[contains(@class,'mcs-btn')]";
		String element=driver.findElement(By.xpath(xpath)).getAttribute("class");
		if(element.contains("x-item-disabled"))
		{
			return true;
		}else{
			return false;
		}
	}
}
