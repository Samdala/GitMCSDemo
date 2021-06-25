package test.framework;

//import java.util.Arrays;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * Failed test can be rerun using this class maxRetryCount is the maximal number
 * of test rerun To use this analyzer include in test annotation the following:
 * 
 * @Test(retryAnalyzer=RetryAnalyzer.class)
 */

public class RetryAnalyzer implements IRetryAnalyzer {

	private int count = 0;
//	private int countDataProvider = 0;
//	private int maxCountDataProvider = 3;
	private int maxCount = 1;
//	private int oldId = 0;

	@Override
	public boolean retry(ITestResult result) {
		
//// if dataprovider contains random values, then test will be retried maxCountDataProvider times
//// if dataprovider contains fixed values (more than maxCountDataProvider) then may be not all tests will be retried 
//// Conclusion: avoid dataproviders with many data in your tests, avoid random data in data provider
//		if (!result.isSuccess()) {
//			countDataProvider++;
//			if (countDataProvider > maxCountDataProvider) {
//				count = 0;
//				countDataProvider = 0;
//				System.out.println("eeennddd");
//				return false;
//				}
//			}
//
//		int id = result.getTestClass().getName().hashCode();
//		id = 31 * id + result.getMethod().getMethodName().hashCode();
//
//		id = 31
//				* id
//				+ (result.getParameters() != null ? Arrays.hashCode(result
//						.getParameters()) : 0);
//
//		if (count == 0) {
//			oldId = id;
//		}
//
//		if (oldId!=id) {
//			count=0;
//			oldId=id;
//		}
//
		
		if (!result.isSuccess()) {
			if (count < maxCount) {
				count++;
				result.setStatus(ITestResult.SUCCESS_PERCENTAGE_FAILURE);
				String message = "<div style='font-weight:normal;color:red;background-color:#ff99ff'>"
						+ Thread.currentThread().getName()
						+ "Error in "
						+ result.getName()
						+ " with status "
						+ result.getStatus()
						+ " Retrying "
						+ count
						+ " times"
						+ "</div><br>\n";
				System.out.println(message);
				Reporter.log(message);
				return true;
			}

		}
//		countDataProvider = 0;
		count = 0;
		return false;

	}

}


//public class Retry implements IRetryAnalyzer {
//	private int retryCount = 0;
//	private int maxRetryCount = 1;
//
//	public boolean retry(ITestResult result) {
//
//		if (!result.isSuccess()) {
//		if (retryCount < maxRetryCount) {
//			retryCount++;
//			Reporter.log(Thread.currentThread().getName() + "Error in "
//					+ result.getName() + " with status "
//					+ result.getStatus() + " Retrying " + retryCount + " times");
//			return true;
//		}
//		}
//		return false;
//	
//	}
//}
