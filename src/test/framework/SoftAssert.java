package test.framework;

import java.io.IOException;
import java.util.Map;



import org.testng.Reporter;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;



public class SoftAssert extends Assertion {
	  private Map<AssertionError, IAssert> m_errors = Maps.newHashMap();
	  private String methodName;
	  public void setMethodName(String method){
		  methodName=method;
	  }
	  
	  @Override
	  public void executeAssert(IAssert a) {
	    try {
	      a.doAssert();
	      Reporter.log("<span style='font-weight:bold;color:#00DF00'> " + a.getMessage()+" :Assert OK: <"+a.getActual()+">  equals(or contains)  <"+ a.getExpected()+">  </span>", true);
	    } catch(AssertionError ex) {
	      m_errors.put(ex, a);
	      try {Reporter.log(" <span style='font-weight:bold;color:#FF0000'>   ERROR: Assert failed: "+ex.getMessage() +" </span>", true);
	    	  AbstractTestSuite.getScreenshot(methodName);} catch (IOException e) {}
	    }
	  }
	 
	  public void assertAll() {
	    if (! m_errors.isEmpty()) {
	      StringBuilder sb =
	            new StringBuilder("The following asserts failed: <br> \n");
	      boolean first = true;
	      for (Map.Entry<AssertionError, IAssert> ae : m_errors.entrySet()) {
	        if (first) {
	          first = false;
	        } else {
	          sb.append("; <br> \n ");
	        }
	        sb.append(ae.getValue().getMessage());
	      }
	      throw new AssertionError(sb.toString());
	    }
	  }
	  
	  public void assertLike(String actualValue, String expectedValue, String message) {
		  boolean bRes = false;
		  bRes = actualValue.toLowerCase().contains(expectedValue.toLowerCase());
		  this.assertEquals(bRes, true, message);  
	  }
	  
	}
