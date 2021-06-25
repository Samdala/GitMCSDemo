package test.generalweb.overviewdefinition;

//import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.McsElement;

public class OverviewDefinitionPageObject extends AbstractMcsTestSuite {

	protected final String XPATH_OVERVIEW_BUTTON = "//button[contains(@class,'x-btn-text icon-ov-overview')]";

	protected final String XPATH_DEFINITION_FRAME = "//iframe[contains(@id,'ovdefdlg_frame')]";

	protected final String XPATH_AREA_RESPONSIBILITY_FRAME = "//iframe[contains(@src,'OV_AREA')]";

	protected final String XPATH_FILTER_FRAME = "//iframe[contains(@src,'OV_FILTER')]";

	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";

	public void goToDefinition(String definition) {
		
		waitForExtJSAjaxComplete(25);

		driver.get(getURLWithoutAQAParam() + "/frame.php?relay=" + definition);
		
		waitForExtJSAjaxComplete(25);
		
		waitForMaskDisappear();
	};

	public void prepareAndClickOverviewButton(String entity) {
		Timer timer = new Timer().start();

                String[] locTypes = new String[]{"site","land","outside","building","floor","room","vroom","workplace"};

                if (Arrays.asList(locTypes).contains(entity)) {
                	
                	 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'x-panel-header-text') and contains(text(),'Advanced Search')]")));
		    driver.findElement(By.xpath("//span[contains(@class,'x-panel-header-text') and contains(text(),'Advanced Search')]")).click();
                    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'icon-" + entity + "')]")));
                    driver.findElement(By.xpath("//button[contains(@class, 'icon-" + entity + "')]")).click();
                }
                new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Overview')]")));
		driver.findElement(By.xpath("//button[contains(text(),'Overview')]")).click();
		waitForExtJSAjaxComplete(20);

                Reporter.log("Overview button was clicked"+ " (" + timer.stop() + "ms)", true);
	}


	public void setName(String name) {
		
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
			driver.findElement(By.xpath("//div[contains(@class,'x-window') and contains(@class, 'x-resizable-pinned')]//label[contains(text(),'Name')]//..//input")).clear();
			driver.findElement(By.xpath("//div[contains(@class,'x-window') and contains(@class, 'x-resizable-pinned')]//label[contains(text(),'Name')]//..//input")).sendKeys(name);
			driver.findElement(By.xpath("//div[contains(@class,'x-window') and contains(@class, 'x-resizable-pinned')]//label[contains(text(),'Name')]//..//input")).click();
			Reporter.log("set name " + name, true);
	}
		

	public String getName() {
		return driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned')]//label[contains(text(),'Name')]//..//input")).getAttribute("value");
	}

	
	
	public void setCategory(String name) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'x-resizable-pinned')]//label[contains(text(),'Category')]//..//input")));

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned')]//label[contains(text(),'Category')]//..//input")).click();
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned')]//label[contains(text(),'Category')]//..//input")).clear();
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned')]//label[contains(text(),'Category')]//..//input")).sendKeys(name);
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned')]//label[contains(text(),'Category')]//..//input")).click();
		Reporter.log("set category " + name, true);
	}

	
	public void clickEnabled() {
		
		WebElement enabledCheckBox = 	driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned')]//label[contains(text(),'Enabled')]//..//input"));
		
		if(!enabledCheckBox.isSelected()){
			
			enabledCheckBox.click();
		}
	}
	
	public void clickPublic() {
		WebElement publicCheckBox = driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned')]//label[contains(text(),'Public')]//..//input"));
		
		if(!publicCheckBox.isSelected()){
			
			publicCheckBox.click();
		}
	}

	
	public String getCategory() {
		return driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned')]//label[contains(text(),'Category')]//..//input")).getAttribute("value");
	}
	
	
	public void save() {
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned')]//button[text()='Save']")).click();
//			McsElement.getElementByAttributeValueAndParentElement(driver, "div",
//					"@class", "x-window x-resizable-pinned", "button", "text()",
//					"Save", true, true).click();
//			waitForMaskDisappear();
	}

	/**
	 * Link text based locator Method allows to wait and click on a button
	 * located by partial link text
	 */
	public void clickAddOverViewButton() {
		String xpath = "//div[contains(@class,'x-menu') and contains(@style,'visible')]//span[text()='Add...']/../..";
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
		Reporter.log("Add Overview button was clicked", true);

	}
}
