// =============================================================================
//  
// Description   : 	Main class for tests for Login functionality, contain common methods.
//


package test.generalweb.login;




import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.webelement.McsElement;




public class LoginPageObject extends AbstractMcsTestSuite {
	
	protected final String CURRENT_PASSWORD_XPATH = "//div[contains(@class,'accchangepwd')]//input[@name='oldPass']";
	
	protected final String 	NEW_PASSWORD_XPATH = "//div[contains(@class,'accchangepwd')]//input[@name='newPass1']";
	
	protected final String 	NEW_PASSWORD2_XPATH = "//div[contains(@class,'accchangepwd')]//input[@name='newPass2']";
	
	protected final String 	SAVE_PASSWORD_XPATH = "//div[contains(@class,'accchangepwd')]//button[contains(text(),'Save')]";
	
	
	
	public void changePassword(String oldPass, String newPass){
		McsElement.getElementByXpath(driver, CURRENT_PASSWORD_XPATH).click();
		McsElement.getElementByXpath(driver, CURRENT_PASSWORD_XPATH).clear();
		McsElement.getElementByXpath(driver, CURRENT_PASSWORD_XPATH).sendKeys(oldPass);
		
		McsElement.getElementByXpath(driver, NEW_PASSWORD_XPATH).click();
		McsElement.getElementByXpath(driver, NEW_PASSWORD_XPATH).clear();
		McsElement.getElementByXpath(driver, NEW_PASSWORD_XPATH).sendKeys(newPass);
		
		McsElement.getElementByXpath(driver, NEW_PASSWORD2_XPATH).click();
		McsElement.getElementByXpath(driver, NEW_PASSWORD2_XPATH).clear();
		McsElement.getElementByXpath(driver, NEW_PASSWORD2_XPATH).sendKeys(newPass);
		
		McsElement.getElementByXpath(driver, SAVE_PASSWORD_XPATH).click();
		
		waitForExtJSAjaxComplete(20);
		
		}
		
		
		
	public void openChangePasswordForm(){
		Reporter.log("Open account menu", true);
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'ext-el-mask')]")));
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='top-account-menu']//button")));
		McsElement.getElementByXpath(driver, "//table[@id='top-account-menu']//button").click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'x-menu x-menu-floating x-layer')]")));
		Reporter.log("Click 'Change Password' menu item", true);
		McsElement.getElementByXpath(driver, "//span[text()='Change Password']").click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'accchangepwd')]")));
		Reporter.log("open change password form", true);
		
		}

		
		
	
}
