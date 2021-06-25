package test.framework;



import java.io.PrintWriter;
import java.io.StringWriter;

import org.testng.Assert;



public class SoftAssertCustom  {
public String error = "";
public int count = 0;
	
	public void assertTrue (boolean check, String mesage){
		try{ Assert.assertTrue(check, mesage);}
		catch(Error e){
			StringWriter writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter( writer );
			e.printStackTrace(printWriter);
			printWriter.flush();
			error+=mesage +" "+ writer.toString();count+=1;
			
		}
	}
  public void assertAll() {
 if (!error.equals("")){
	 throw new AssertionError("There were "+count +" errors:  "+error);
 }
}
}