package test.generalweb.reservation;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.AbstractMcsTestSuite;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.McsElement;


public class ReservationNavigationPageObject extends ReservationPageObject {
	

	
	public void verifyWelcomePage(String welcomeText) {
		WebElement iFrame = McsElement.getElementByXpath(driver, "//div[contains(@class, 'reservation')]//iframe");
		driver.switchTo().frame(iFrame);
		McsElement.getElementByXpath(driver, "//h1[contains(text(),'" + welcomeText + "')]");
		driver.switchTo().defaultContent();
		Reporter.log("welcomeText was found <br>", true);
	}
	
	public void verifyButtonPressedPage(String buttonText) {
		McsElement.getElementByXpath(driver, "//table[contains(@class, 'pressed')]//em//a[contains(text(), '" + buttonText + "')]");
		Reporter.log("My reservation button is pressed <br>", true);
}
}