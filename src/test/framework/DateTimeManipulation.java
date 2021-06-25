package test.framework;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateTimeManipulation {
    
	/**
	 * Method allows to get increased\decreased date by date format
	 * returns updated date time - name of the input for which lookup is opened 
	 * 
	 * @param dt - date time e.g. current time 
	 * @param fieldType - HOUR\DAY\MONTH (2\10\5) 
	 * @param increment - increase\decrease period
	 * @param stringDateFormat - format date e.g. "dd-MM-yyyy"
	 */
	public String add(String dt, int fieldType,  int increment, String stringDateFormat) {
		//fieldType  - HOUR = 10;
		//fieldType  - DATE = 5;

		SimpleDateFormat sdf = new SimpleDateFormat(stringDateFormat);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fieldType == 5) {
			c.add(Calendar.DATE, increment);  // number of days increment to add// could be negative value 
		} else {
		    c.add(Calendar.HOUR, increment);  // number of days increment to add// could be negative value 
		}
		
		dt = sdf.format(c.getTime());  // dt is now the new date
		return dt;
    }
	
	public int getDayOfMonth(String dayAheadAfterToday) {
	    Calendar cal = Calendar.getInstance();
	    @SuppressWarnings("deprecation")
		Date date = new Date(dayAheadAfterToday);
	    cal.setTime(date);
	    return cal.get(Calendar.DAY_OF_MONTH);
	}

}
