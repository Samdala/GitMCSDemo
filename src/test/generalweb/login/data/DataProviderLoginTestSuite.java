// =============================================================================
//  
// Description   : 	Data Provider class for tests for Login functionality, contain 
//					common methods for read tests data from appropriate data files.
//
// =============================================================================
//  Copyright(c) 2012
// =============================================================================

package test.generalweb.login.data;

/**
 * @author Ivanovskyy Oleh 
 * @version 1.0
 */

import java.util.Iterator;


import org.testng.annotations.DataProvider;


import test.framework.dataproviders.MsExcelDataProvider;

public class DataProviderLoginTestSuite  {
	
	protected static String packageName = "generalweb/login/data/"; 
	
	
	@DataProvider(name = "incorrectCredentialsDataProvider")
	public static Iterator<Object[]> incorrectCredentialsDataProvider() throws Exception {
		return MsExcelDataProvider.getTestData(packageName + "incorrectCredentialsLogin_");
	}
	
	@DataProvider(name = "correctCredentialsDataProvider")
	public static Iterator<Object[]> correctCredentialsDataProvider() throws Exception {
		return MsExcelDataProvider.getTestData(packageName + "correctCredentialsLogin_");
	}
	
	@DataProvider(name = "recoveryEmailIncorrect")
	public static Iterator<Object[]> recoveryEmailIncorrect() throws Exception {
		return MsExcelDataProvider.getTestData(packageName + "recoveryEmailIncorrect_");
	}
	
	@DataProvider(name = "recoveryEmailCorrect")
	public static Iterator<Object[]> recoveryEmailCorrect() throws Exception {
		return MsExcelDataProvider.getTestData(packageName + "recoveryEmailCorrect_");
	}
	
	@DataProvider(name = "recoveryPageGui")
	public static Iterator<Object[]> recoveryPageGui() throws Exception {
		return MsExcelDataProvider.getTestData(packageName + "recoveryPageGui_");
	}
	
	@DataProvider(name = "loginPageGui")
	public static Iterator<Object[]> loginPageGui() throws Exception {
		return MsExcelDataProvider.getTestData(packageName + "loginPageGui_");
	}
}
