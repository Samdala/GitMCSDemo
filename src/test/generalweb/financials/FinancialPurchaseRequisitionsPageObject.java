package test.generalweb.financials;

import test.framework.webelement.McsElement;
import test.generalweb.purchaserequisitions.PurchaseRequisitionsPageObject;

public class FinancialPurchaseRequisitionsPageObject extends  PurchaseRequisitionsPageObject {


	/**
	 * Checking whether Purchase Requisition Window is opened or not
	 **/
	public boolean isPurchaseRequisitionWindowOpen()
	{
		waitForExtJSAjaxComplete(20);
		String xpath = "//div[contains(@class,'x-window x-resizable-pinned')]//span[text()='New Purchase Requisition']";
		return McsElement.isElementDisplayed(driver, xpath);
	}
}
